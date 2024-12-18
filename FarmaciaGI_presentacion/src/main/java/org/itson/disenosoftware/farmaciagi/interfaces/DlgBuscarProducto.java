package org.itson.disenosoftware.farmaciagi.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.GestorProductos;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.IGestorProductos;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.excepciones.GestorProductosException;
import org.itson.disenosoftware.farmaciagi_subsistema_promociones.GestorPromociones;
import org.itson.disenosoftware.farmaciagi_subsistema_promociones.IGestorPromociones;

public class DlgBuscarProducto extends javax.swing.JDialog {

    private IGestorProductos gestorInventario;
    private IGestorPromociones gestorPromociones;
    private List<ProductoDTO> productosVenta;
    private List<PromocionDTO> promocionesVenta;

    public DlgBuscarProducto(java.awt.Frame parent, boolean modal, List<ProductoDTO> productosVenta, List<PromocionDTO> promocionesVenta) {
        super(parent, modal);
        this.gestorInventario = new GestorProductos();
        this.gestorPromociones = new GestorPromociones();
        this.productosVenta = productosVenta;
        this.promocionesVenta = promocionesVenta;
        initComponents();
        btnCerrar.setBackground(Color.WHITE);
        btnBuscarProducto.setBackground(Color.WHITE);
        centraCuadroDialogo(parent);
    }

    /**
     * Este método centra el cuadro de dialogo sobre la ventana de la
     * aplicación.
     *
     * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
     */
    private void centraCuadroDialogo(java.awt.Frame parent) {
        // Obtiene el tamaño y posición de la ventana de la aplicación
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        // Obtiene el tamaño del cuadro de diálogo
        Dimension dlgSize = getPreferredSize();
        // Centra el cuadro de diálogo sobre la ventana padre
        setLocation((frameSize.width - dlgSize.width) / 2 + loc.x, (frameSize.height - dlgSize.height) / 2 + loc.y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusqueda = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        fondo.setBackground(new java.awt.Color(216, 215, 255));
        fondo.setMaximumSize(new java.awt.Dimension(812, 600));
        fondo.setMinimumSize(new java.awt.Dimension(800, 600));
        fondo.setPreferredSize(new java.awt.Dimension(800, 600));

        btnBuscarProducto.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lupa_pequeña.png"))); // NOI18N
        btnBuscarProducto.setText("| BUSCAR");
        btnBuscarProducto.setFocusPainted(false);
        btnBuscarProducto.setPreferredSize(new java.awt.Dimension(36, 36));
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUSCAR PRODUCTO");

        btnCerrar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCerrar.setText("CERRAR");
        btnCerrar.setFocusPainted(false);
        btnCerrar.setMaximumSize(new java.awt.Dimension(100, 30));
        btnCerrar.setPreferredSize(new java.awt.Dimension(173, 48));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        tblBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tblBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ARTICULO", "MARCA", "COSTO", "CANTIDAD", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBusqueda.setName("Busqueda Producto"); // NOI18N
        jScrollPane1.setViewportView(tblBusqueda);

        txtBuscar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtBuscar.setPreferredSize(new java.awt.Dimension(36, 37));

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel1))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        List<ProductoDTO> productosBuscados = new LinkedList<>();
        if (!txtBuscar.getText().isBlank()) {

            productosBuscados = gestorInventario.buscarProductosPorNombre(txtBuscar.getText());

        } else {
            JOptionPane.showMessageDialog(rootPane, "Debes ingresar el nombre o clave del producto",
                    "Asegurate de no tener la casila vacía", JOptionPane.INFORMATION_MESSAGE);
        }
        if (!productosBuscados.isEmpty()) {
            llenarTabla(productosBuscados);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay productos en el inventario.",
                    "Inventario vacío", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    //Métodos 
    private void llenarTabla(List<ProductoDTO> productosBuscados) {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ARTICULO");
        modelo.addColumn("MARCA");
        modelo.addColumn("COSTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("");

        for (ProductoDTO producto : productosBuscados) {
            Object[] fila;
            if (producto.getCantidad() != null && producto.getCantidad() > 0) {
                fila = new Object[]{
                    producto.getNombre(),
                    producto.getMarca(),
                    producto.getCosto(),
                    producto.getCantidad(),
                    "AGREGAR"
                };
            } else {
                fila = new Object[]{
                    producto.getNombre(),
                    producto.getMarca(),
                    producto.getCosto(),
                    producto.getCantidad(),
                    "AGOTADO"
                };
            }
            modelo.addRow(fila);
        }
        tblBusqueda.setModel(modelo);
        TableColumnModel columnModel = tblBusqueda.getColumnModel();

        ButtonColumn buttonColumn = new ButtonColumn("AGREGAR", (e) -> {
            int fila = tblBusqueda.convertRowIndexToModel(tblBusqueda.getSelectedRow());
            ProductoDTO productoAgregado = productosBuscados.get(fila);
            if (productoAgregado.getCantidad() != null && productoAgregado.getCantidad() > 0) {
                ProductoDTO productoCopia = new ProductoDTO(
                        productoAgregado.getCodigo(),
                        productoAgregado.getNombre(),
                        productoAgregado.getCosto(),
                        productoAgregado.getMarca()
                );

                agregarProductoVenta(productoCopia);

                Integer cantidad = productoAgregado.getCantidad();
                productoAgregado.setCantidad(- 1);

                try {
                    gestorInventario.modCantidadProducto(productoAgregado);
                    productoAgregado.setCantidad(cantidad - 1);
                } catch (GestorProductosException ex) {
                    Logger.getLogger(DlgBuscarProducto.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (productoAgregado.getCantidad() == 0) {
                    productosBuscados.remove(productoAgregado);
                }
                agregarPromocion(productoAgregado);
            }
            llenarTabla(productosBuscados);

        });

        tblBusqueda.getColumnModel().getColumn(tblBusqueda.getColumnCount() - 1).setCellRenderer(buttonColumn);
        tblBusqueda.getColumnModel().getColumn(tblBusqueda.getColumnCount() - 1).setCellEditor(buttonColumn);

    }

    private void agregarProductoVenta(ProductoDTO producto) {
        if (productosVenta.contains(producto)) {
            int indice = productosVenta.indexOf(producto);
            ProductoDTO productoEncontrado = productosVenta.get(indice);

            productoEncontrado.setCantidad(productoEncontrado.getCantidad() + 1);

            productosVenta.set(indice, productoEncontrado);
        } else {
            producto.setCantidad(1);
            productosVenta.add(producto);
        }
    }

    private void agregarPromocion(ProductoDTO productoAgregado) {
        List<PromocionDTO> promocionesRegistro = gestorPromociones.obtenerPromociones();
        for (PromocionDTO promocion : promocionesRegistro) {
            if (productoAgregado.equals(promocion.getProducto())) {
                if ((productosVenta.get(productosVenta.indexOf(productoAgregado)).getCantidad() % promocion.getCantidad()) == 0) {
                    promocionesVenta.add(promocion);
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBusqueda;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
